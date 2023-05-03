var noSelect = '<li class="d-flex gap-4">\n' +
    '                                        <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" fill="currentColor" class="bi bi-exclamation-circle" viewBox="0 0 16 16">\n' +
    '  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>\n' +
    '  <path d="M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0zM7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 4.995z"/>\n' +
    '</svg>\n' +
    '                                        <div>\n' +
    '                                            <h5 class="mb-0">No Items Selected 🤨</h5>\n' +
    '                                            Start by clicking on foods!\n' +
    '                                        </div>\n' +
    '                                    </li>';


function action(classname) {
    $("." + classname).removeClass("d-none");
    $('#row > *').not($('.' + classname)).addClass('d-none');
}
function openAll() {
    $('#row > *').removeClass('d-none');
}


function plus(idd) {
    let value = parseInt($("input[name='count']#" + idd).val());
    if (value < 5) {
        $('input#' + idd).val(value + 1);
    }
}

function minus(idd) {
    let value = parseInt($("input[name='count']#" + idd).val());
    if (value > 0) {
        $('input#' + idd).val(value - 1);
    }
}


//{"productOrders":[{"product":{"name":"Whopper"},"quantity":3},{"product":{"name":"Coca-Cola"},"quantity":5}]}
function create(product, quantity) {
    return '{"product":{"name":"' + product + '"},"quantity":' + quantity + '}';
}

function createFood(product, quantity, price, image) {
    return '{"product":{"name":"' + product + '"},"quantity":' + quantity + ',"price":' + price + ',"image":"' + image + '"}';
}

function generate(productArray) {
    let empty = '{"productOrders":[';

    for (let i = 0; i < productArray.length; i++) {
        let object = JSON.parse(productArray[i])
        let food = createFood(object.product.name, object.quantity, object.price);
        if (productArray.length - 1 === i) {
            empty += food
        } else {
            empty += food + ',';

        }

    }
    return empty + ']}';

}


function addToCart(idd) {
    let products = JSON.parse(readCookie("products"))
    if (products == null) {
        products = []
    }
    let count = parseInt($("input[name='count']#" + idd).val());
    let price = parseInt($("input." + idd).val());
    let image = $("#food-img-" + idd).attr('src');


    if (count > 0) {
        let product = createFood(idd, count, price, image);

        for (let i = 0; i < products.length; i++) {
            let food = JSON.parse(products[i]);
            if (food.product.name === idd) {
                let minus = parseInt($('#purchase').text()) - (price * food.quantity);
                $('#purchase').text(minus.toString());
                document.getElementById("cart-item" + idd).remove();
                products.splice(i, 1);
            }
        }
        products.push(product)
        eraseCookie("products")
        createCookie("products", JSON.stringify(products), 1);

        let item = $('#items').text();
        if (item.includes("No Items Selected")) {
            $('#items').text("");
        }

        $('#items')
            .append('<li id="cart-item' + idd + '" class="d-flex gap-4"><img src="' + image + '" style="width: 48px;height:48px;"><div><h5 class="mb-0">' + idd + '</h5>' + count + ' x ' + price + ' = ' + count * price + '</div></li>')

        let after = parseInt($('#purchase').text())
        let overall = (price * count) + after;
        showContent(overall)
        $('#cart-size').text(products.length);
        $('#catalog-size').text(products.length);
        $('#catalog-size-badge').text(products.length);



    } else {
        alert("Firstly,choose the quantity")

    }


}

function addToCartOne(idd) {
    let products = JSON.parse(readCookie("products"))
    if (products == null) {
        products = []
    }
    let count = 1;
    let price = parseInt($("input." + idd).val());
    let image = $("#food-img-" + idd).attr('src');
    $("input[name='count']#" + idd).val(1)

    if (count > 0) {
        let product = createFood(idd, count, price, image);

        for (let i = 0; i < products.length; i++) {
            let food = JSON.parse(products[i]);
            if (food.product.name === idd) {
                let minus = parseInt($('#purchase').text()) - (price * food.quantity);
                $('#purchase').text(minus.toString());
                document.getElementById("cart-item" + idd).remove();
                products.splice(i, 1);
            }
        }
        products.push(product)
        eraseCookie("products")
        createCookie("products", JSON.stringify(products), 1);

        let item = $('#items').text();
        if (item.includes("No Items Selected")) {
            $('#items').text("");
        }

        $('#items')
            .append('<li id="cart-item' + idd + '" class="d-flex gap-4"><img src="' + image + '" style="width: 48px;height:48px;"><div><h5 class="mb-0">' + idd + '</h5>' + count + ' x ' + price + ' = ' + count * price + '</div></li>')

        let after = parseInt($('#purchase').text())
        let overall = (price * count) + after;
        showContent(overall)
        $('#cart-size').text(products.length);
        $('#catalog-size').text(products.length);
        $('#catalog-size-badge').text(products.length);


    } else {
        alert("Firstly,choose the quantity")

    }


}

function animation() {
    let preloader = document.querySelector('.preloader');
    preloader.style.display = 'flex';
    setTimeout(() => {
        preloader.remove();
    }, 600);
}

function checkSaveProducts() {
    if (readCookie("products") != null) {
        let overall = 0;
        let save = JSON.parse(readCookie("products"))
        //{"product":{"name":"Whopper"},"quantity":1,"price":1000},{"product":{"name":"Pepsi-Cola"},"quantity":1,"price":350}
        let savelength = parseInt(save.length);

        $('#cart-size').text(savelength);
        $('#catalog-size-badge').text(savelength);
        $('#catalog-size').text(savelength);

        for (let i = 0; i < savelength; i++) {
            let saveProduct = JSON.parse(save[i]);
            let idd = saveProduct.product.name;
            let count = saveProduct.quantity;
            let price = saveProduct.price;
            let image = saveProduct.image;
            addProductInfo(idd, count, price, image);
            overall += (count * price);
        }
        showContent(overall)
    } else {
        noSelected();
    }
}

function addProductInfo(idd, count, price, image) {
    $('#cart-actions-' + idd).removeClass('d-none');
    $('.see-info' + idd).addClass('d-none')
    $('input#' + idd).val(count);
    $('#items')
        .append('<li id="cart-item' + idd + '" class="d-flex gap-4"><img src="' + image + '" style="width: 48px;height:48px;"><div><h5 class="mb-0">' + idd + '</h5>' + count + ' x ' + price + ' = ' + count * price + '</div></li>')
}

function showContent(overall) {
    $('#purchase').text(overall.toString())
    $('.options').removeClass("d-none");
}


function noSelected() {
    $('#items').text("");
    $('#items').append(noSelect)
}

function buy() {
    let json = generate(JSON.parse(readCookie("products")));
    console.log(json)
    animation();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/test/" + parseInt(chairCheck()),
        data: json,
        dataType: "json",
        cache: false,
        timeout: 6000,
        success: function (data) {
            eraseCookie("products")
            console.log(data.message)
            document.location.href = '/orders/view/' + data.message

        },
        error: function (response, error, errorThrown) {
            console.log(response, error, errorThrown)
            alert("Error During Purchase")
        }
    });

}


function styles(mode) {
    $('#ajaxreader').removeClass("d-none")
    if (mode == "success") {
        $('#ajaxreader').addClass("alert alert-success alert-dismissible fade show")
    } else {
        $('#ajaxreader').addClass("alert alert-danger alert-dismissible fade show")

    }
    $('#ajaxreader').addClass("alert alert-success alert-dismissible fade show")
    $('body>*').not($('#layer')).css("opacity", "0.5")


}


function resetCart() {
    eraseCookie("products")

    $("input[name='count']").val(0)
    $('#cart-size').text('0');
    $('#catalog-size').text('0');
    $('#catalog-size-badge').text('0');

    $('#purchase').text("0");

    noSelected()

    $('.options').addClass("d-none");

}

function action2() {
    $('#ajaxreader').addClass('d-none');
    $('body>*').css("opacity", "1")
}

// localhost:8081/catalog?chair=2

function chairCheck() {
    if (getParameterByName('chair') != null) {
        return getParameterByName('chair');
    } else {
        return 0;
    }

}

function getParameterByName(name, url = window.location.href) {
    name = name.replace(/[\[\]]/g, '\\$&');
    var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
}


function createCookie(name, value, days) {
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        var expires = "; expires=" + date.toGMTString();
    } else var expires = "";
    document.cookie = name + "=" + value + expires + "; path=/";
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

function eraseCookie(name) {
    createCookie(name, "", -1);
}

function deleteOrder(id) {
    var form = document.getElementById("delete-form " + id);
    var choice = confirm("The order would be deleted");
    if (choice) {
        form.submit();
    }

    function lastAccessedOrder() {
        if (getParameterByName("last") != null) {
            scrollToElement(getParameterByName("last"));
        }
    }

    function scrollToElement(idd) {
        $('html, body').animate({
            scrollTop: $("#order-" + idd).offset().top
        }, 2000);
    }


}
function checkCart() {
    if (readCookie("products") != null) {
        let save = JSON.parse(readCookie("products"))
        let savelength = parseInt(save.length);
        $('#catalog-size').text(savelength);
        $('#catalog-size-badge').text(savelength);
    }
}



// window.onload = function () {
//     checkSaveProducts();
// };
