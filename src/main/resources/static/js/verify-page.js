function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode === 43) {
        return true;
    } else if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }

    return true;
}

function setDetail(status, message) {
    $('#exampleModalLabel').text(status);
    $('.modal-body ').text(message);
}

function sendPhoneNumber() {
    var phoneNumber = {};
    phoneNumber["phoneNumber"] = $('#inputTel').val();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "api/v1/sms/code",
        data: JSON.stringify(phoneNumber),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            $('#verify-button').click();
            $('#verify-button').removeClass("d-none");
        },
        error: function (response, error, errorThrown) {
            if (response.responseText === 'Already sent...Check the phone') {
                $('#verify-button').click();
                $('#verify-button').removeClass("d-none");
            } else {
                alert(response.responseText);
            }
        }
    });
}


function checkThePinCode() {
    var code = {};
    code["code"] = $('#pincode').val();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "api/v1/sms/check",
        data: JSON.stringify(code),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log(data)
            $('#txtUsername').val(data.username)
            $('#txtPassword').val(data.password)
            $('#forLogin').submit()
            $('#verify-button').addClass("d-none")

        },
        error: function (response, error, errorThrown) {
            alert(response.responseText)
        }
    });
}
