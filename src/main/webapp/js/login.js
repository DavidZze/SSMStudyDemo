/**
 * Created by David.ze on 16/9/12.
 */
$(document).ready(function() {
    $("#profile").click(function() {
        console.log('do profile func() >>>> ');
        profile();
    });
    $("#login").click(function() {
        console.log('do login func() >>>> ');
        login();
    });
});


function profile() {
    var url = 'http://localhost:8080/TestApp/person/profile/';
    var query = $('#id').val() + '/' + $('#name').val() + '/'
        + $('#status').val();
    url += query;
    // alert(url);
    console.log(url);
    $.get(url, function(data) {
        console.log("id: " + data.id + "\nname: " + data.name + "\nstatus: "
            + data.status);
    });
};



function login() {
    var mydata = '{"name":"' + $('#name').val() + '","id":"'
        + $('#id').val() + '","status":"' + $('#status').val() + '"}';
    // alert(mydata);
    console.log(mydata);
    $.ajax({
        type : 'POST',
        contentType : 'application/json; charset=UTF-8',
        url : 'http://localhost:8080/TestApp/person/login',
        processData : false,
        dataType : 'json',
        data : mydata,
        success : function(data) {
            console.log("id: " + data.id + "\nname: " + data.name + "\nstatus: "
                + data.status);
        },
        error : function() {
            console.log('Err...');
        }
    })};