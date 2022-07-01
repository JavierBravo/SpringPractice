$(document).ready(function(){
});

async function registerUser(){
    let inputBody = {};
    inputBody.nombre = document.getElementById('txtNombre').value;
    inputBody.apellido = document.getElementById('txtApellido').value;
    inputBody.email = document.getElementById('txtEmail').value;
    inputBody.telefono = document.getElementById('txtTelefono').value;
    inputBody.password = document.getElementById('txtContraseña').value;

    let passwordRepeat = document.getElementById('txtRepetirContraseña').value;
    if (passwordRepeat != inputBody.password){
        alert('Las contraseñas ingresadas no coinciden.');
        return;
    }
    const request = await fetch('api/users', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(inputBody)
    });
    location.href='/users.html';
}