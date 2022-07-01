async function editUser(){
    let userBody = {};
    userBody.id = document.getElementById('txtId').value;

    if(document.getElementById('txtNombre').value != null){
        userBody.nombre = document.getElementById('txtNombre').value;
    }
    if(document.getElementById('txtApellido').value != null){
        userBody.apellido = document.getElementById('txtApellido').value;
    }
    if(document.getElementById('txtNombre').value != null){
        userBody.telefono = document.getElementById('txtTelefono').value;
    }

    if (confirm('¿Desea modificar al usuario ' +userBody.id+ '?')){
        const request = await fetch('api/users/' +userBody.id, {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'Application/json',
                'Authorization': localStorage.token
            },
            body: JSON.stringify(userBody)
        });
    }
    location.href='/users.html';
}