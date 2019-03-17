(function ($) {
    var input = document.getElementById('currentPosition');

    input.oninput = function () {
        var data = {};
        var command = 'AJAX_CUR_STREET';
        data = {"command": command, "curStreet": $("#currentPosition").val()}
        $.ajax
        ({
            type: "POST",
            data: data,
            url: '/Taxi/order',
            success: function (serverData) {
                console.log(serverData.answer);
                var obj = document.getElementById('forCurPos1');
                obj.value = serverData.answer;
            },
            error: function () {
                console.log(data);
            }
        })
        // document.getElementById('nextPosition').value = input.value;
    };
})