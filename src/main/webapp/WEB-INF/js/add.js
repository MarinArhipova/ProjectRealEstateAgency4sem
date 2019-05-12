$(".submit_feedback").click(function () {
    $.ajax({
        type: 'post',
        url: '/basket',
        data: {
            productId: $(this).attr("data-id")
        }
    })
});