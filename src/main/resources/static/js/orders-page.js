function checkCart() {
    if (readCookie("products") != null) {
        let save = JSON.parse(readCookie("products"))
        let savelength = parseInt(save.length);
        $('#catalog-size').text(savelength);
    }
}

function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1, c.length);
        }
        if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
}

function typeBuilder(idd, type) {
    return '{"orderDto":{"id":' + idd + '},"type":"' + type + '"}';
}

function cash(idd) {
    let orderTypeDto = typeBuilder(idd, "WITHCASH");
    ajax(orderTypeDto)
}

function waiter(idd) {
    let orderTypeDto = typeBuilder(idd, "WITHWAITER");
    ajax(orderTypeDto)

}

function ajax(content) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/paytype",
        data: content,
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            var json = "<h4>Payment</h4><button class='btn btn-primary'>" + "<a href='/api/orders' style='color: white;'>" + response.responseText + "</a></button>";
            $('#message').html(json);
            styles("success");
        },
        error: function (response, error, errorThrown) {
            var json = "<h4>Payment</h4><button class='btn btn-primary'>" + "<a href='/api/orders' style='color: white;'>" + response.responseText + "</a></button>";
            $('#message').html(json);
            styles("danger");
        }
    });
}

function lastAccessedOrder() {
    if (getParameter("last") != null) {
        let orderId = parseInt(getParameter("last"));
        let element = $("#order-" + orderId);
        $('html, body').animate({
            scrollTop: element.offset().top - element.height() / 2
        }, 1500);
        $('button#' + orderId).click()
    }

}


function getParameter(name, url = window.location.href) {
    name = name.replace(/[\[\]]/g, '\\$&');
    var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
}
checkCart()
lastAccessedOrder()
