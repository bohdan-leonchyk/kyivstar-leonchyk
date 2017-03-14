$('select').material_select();
var $selectDropdown = $("#select_webcam");
$('.modal').modal({
        dismissible: true, // Modal can be dismissed by clicking outside of the modal
        opacity: .5, // Opacity of modal background
        inDuration: 300, // Transition in duration
        outDuration: 200, // Transition out duration
        startingTop: '4%', // Starting top style attribute
        endingTop: '10%', // Ending top style attribute
        ready: function (modal, trigger) { // Callback for Modal open. Modal and trigger parameters available.
            // console.log(modal, trigger);
            $.ajax({
                type: 'GET',
                url: '/webcams',
                data: {get_param: 'value'},
                dataType: 'json',
                success: function (data) {
                    if (data != null) {

                        $selectDropdown = $("#select_webcam").empty().html(' ');
                        $selectDropdown.append($('<option>', {value:'default', text:'Select webcam'}));

                        $.each(data, function (key, val) {
                            $selectDropdown.append($('<option>', {value:val.identifier, text:val.identifier}));
                        });
                        $selectDropdown.trigger('contentChanged');

                    }
                }
            });
        },
        complete: function () {

        }
    }
);

$('select').on('contentChanged', function() {
    $(this).material_select();
});

$('#myForm').submit(function(e) {
    $.ajax({
        url: '/pictures/'+$selectDropdown.val(),
        type: 'POST',
        data: new FormData(this),
        processData: false,
        contentType: false,
        success: function () {
            $('#pic_status').attr('class','green-text');
            $('#pic_status').text($('#fileinput').val() + ' was succesfully saved.');
        },
        error: function () {
            $('#pic_status').attr('class','red-text');
            $('#pic_status').text('Could not save ' + $('#fileinput').val());
        }
    });
    e.preventDefault();
});