
//                <tr class="row mx-auto">
//                    <th class="col-md-2 text-center">1</th>
//                    <td class="col-md-3 text-center">spaska</td>
//                    <td class="col-md-2 text-center">spaska@abv.bg</td>
//                    <td class="col-md-2 text-center">USER</td>
//                    <td class="col-md-3 text-center">
//                        
//                            <div class="row">
//                                <form action="/users/set-moderator/7f8f5642-9b95-41b2-8508-e91b50fdc12a" method="post" class="mx-1"><input type="hidden" name="_csrf" value="80417983-fe8b-45c7-963e-31f6915a1560">
//                                    <button class="btn btn-dark">MODERATOR</button>
//                                </form>
//                                <form action="/users/set-admin/7f8f5642-9b95-41b2-8508-e91b50fdc12a" method="post" class="mx-1"><input type="hidden" name="_csrf" value="80417983-fe8b-45c7-963e-31f6915a1560">
//                                    <button class="btn btn-dark">ADMIN</button>
//                                </form>
//                            </div>
//                        
//                        
//                        
//                    </td>
//                </tr>



let URL = {
    getUsers: "/api/allUsers"
}

function userAuthorities(authorities) {
    let result = [];
    for(let i = 0; i < authorities.length; i++) {
        result.push(authorities[i].authority);
    }
    return result;
}


function getRolesOptions(authorities, email) {
    const allAuthorities = ["ADMIN", "USER", "ROOT", "MODERATOR"];
    const currentUserAuthorities = userAuthorities(authorities);
    let element =``;

    allAuthorities.forEach(auth => {
        if(!currentUserAuthorities.includes(auth)) {
            element+=
                `<form action="/api/users/set-role/${auth.toLowerCase()}/${email}" method="post" class="mb-1 mx-1"><input type="hidden" name="_csrf" value="80417983-fe8b-45c7-963e-31f6915a1560">
                     <button class="btn btn-dark">${auth}</button>
                </form>`;
        }
    })
    return element;
}

fetch(URL.getUsers)
    .then((response) => {
        return response.json();
    })
    .then((users) => {
        users.forEach(user => {
            console.log(user);
          
                    $("#users").append(`<tr class="row mx-auto">
                   <th class="col-md-2 text-center"></th>
                   <td class="col-md-3 text-center">${user.email.split('@')[0]}</td>
                   <td class="col-md-2 text-center">${user.email}</td>
                   <td class="col-md-2 text-center">${userAuthorities(user.authorities).join(" ,")}</td>
                   <td class="col-md-3 text-center">
                        <div class="row">
                            ${getRolesOptions(user.authorities, user.email)}
                        </div>
                          
                   </td>
               </tr>`)
        });
    })




