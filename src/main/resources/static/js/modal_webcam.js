$("input:text").keyup(function() {
    $(this).val($(this).val().replace(/[^a-z0-9]/gi, ''));
});

function saveWebcamButton() {
    var identifier = $('#webcam_identifier').val();
    var location = ($('#webcam_location').val().length == 0 ? '-' : $('#webcam_location').val());

    var dataObject = {
        'identifier' : identifier,
        'location' : location
    };

    if (identifier.replace(/[^a-z0-9]/gi, '').length > 0) {
        $.ajax({
            url: '/webcams/' + identifier + '/' + $('#webcam_location'),
            type: 'POST',
            data: JSON.stringify(dataObject),
            contentType: 'application/json',
            success: function () {
                $('#status').attr('class','green-text');
                $('#status').text('Webcam \'' + identifier + '\' was saved.');
                $('#webcam_identifier').val("");
                $('#webcam_location').val("");
            },
            error: function () {
                $('#status').attr('class','red-text');
                $('#status').text('Webcam \'' + identifier + '\' already exists.');
            }
        });
    } else {
        $('#status').attr('class','red-text');
        $('#status').text('Enter valid identifier name');
    }
}


