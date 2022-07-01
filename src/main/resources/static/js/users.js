// Call the dataTables jQuery plugin
$(document).ready(function() {
    document.getElementById('txtUserEmail').outerHTML = localStorage.email;
    cargarUsuarios();
  $('#users').DataTable();
});

async function cargarUsuarios(){
    const request = await fetch('api/users', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'Application/json',
            'Authorization': localStorage.token
        }
    });

    const users = await request.json();
    let htmlList = '';
    for(let user of users){
       let btnDelete = '<a href="#" onclick="eliminarUsuario(' +user.id+ ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
        let telefonoTexto = user.telefono == null ? '-' : user.telefono;
        let userHTML = '<tr><td>' +user.id+ '</td><td>' +user.nombre+ ' ' +user.apellido+ '</td><td>' +user.email+ '</td><td>' + telefonoTexto + '</td><td>' +btnDelete+ '</td></tr>';
        htmlList += userHTML;
        }
    document.querySelector('#users tbody').outerHTML = htmlList;
}

async function eliminarUsuario(id){
    if(confirm('¿Desea eliminar este usuario?')){
        const request = await fetch('api/users/' + id, {
                    method: 'DELETE',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'Application/json',
                        'Authorization': localStorage.token
                    }
                });
        location.reload();
    }else {return;}
}