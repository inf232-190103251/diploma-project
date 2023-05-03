var prevState = undefined;

function clickHolder() {
    $('.id-holder').click()
}

function getStatus(id) {
    let newId = parseInt(id);
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/orders/status/" + newId,
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log(data)
        },
        error: function (response, error, errorThrown) {
            let res = JSON.parse(response.responseText);
            if (prevState === undefined) {
                prevState = res.status
                if (res.status === "PAID") {
                    $('#order-detail').text("Order is paid, would be ready on " + res.predictedDate)
                    $('#launch').click()
                } else if (res.status === "DONE") {
                    $('#order-detail').text("Order is done")
                    $('#launch').click()
                }
            } else if (res.status !== prevState) {
                prevState = res.status
                if (res.status === "PAID") {
                    $('#order-detail').text("Order is paid, would be ready on " + res.predictedDate)
                    $('#launch').click()
                } else if (res.status === "DONE") {
                    $('#order-detail').text("Order is done")
                    $('#launch').click()
                }
            }
        }
    });
    setTimeout(function () {
        getStatus(id)
    }, 5000)
}
