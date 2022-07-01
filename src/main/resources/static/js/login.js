$(document).ready(function() {
});

async function login(){
    let inputBody = {};
    inputBody.email = document.getElementById('txtEmail').value;
    inputBody.password = document.getElementById('txtContraseña').value;

    const request = await fetch('api/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(inputBody)
    });
    const response = await request.text();
    if (response != 'FAIL'){
        localStorage.token = response;
        localStorage.email =  inputBody.email;
        window.location.href = 'users.html';
    }else{
        alert("Ingresó un email o una contraseña incorrecta.");
    }
}