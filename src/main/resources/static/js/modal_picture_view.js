$('select').material_select();
var $selectWebcamDropdown = $("#select_view_webcam");
var $selectPictureDropdown = $("#select_picture");
$("#modal_picture_view").modal({
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

                        $selectWebcamDropdown = $("#select_view_webcam").empty().html(' ');
                        $selectWebcamDropdown.append($('<option>', {value:'default', text:'Select webcam'}));

                        $.each(data, function (key, val) {
                            $selectWebcamDropdown.append($('<option>', {value:val.identifier, text:val.identifier}));
                        });
                        $selectWebcamDropdown.trigger('contentChanged');
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

function refreshPictures() {
    $.ajax({
        type: 'GET',
        url: '/pictures/'+$selectWebcamDropdown.val(),
        data: {get_param: 'value'},
        dataType: 'json',
        success: function (data) {
            if (data != null) {

                $selectPictureDropdown = $("#select_picture").empty().html(' ');

                $.each(data, function (key, val) {
                    $selectPictureDropdown.append($('<option>', {value:val.name, text:val.name}));
                });
                $selectPictureDropdown.trigger('contentChanged');
            } else {
                $selectPictureDropdown = $("#select_picture").empty().html(' ');
                $selectPictureDropdown.append($('<option>', {value:'default', text:'No pictures for this webcam'}));
                $selectPictureDropdown.trigger('contentChanged');
            }
        }
    });
}



function showPicture() {
    $.ajax({
        type: 'GET',
        url: '/pictures/'+ $selectWebcamDropdown.val() +'/' + $selectPictureDropdown.val() +'/base64',
        data: {get_param: 'value'},
        contentType: 'text',
        success: function (data) {
            $("#preview").attr("src","data:image/jpg;base64," + data);
        },
        error: function () {
            alert("error");
        }
    });
}