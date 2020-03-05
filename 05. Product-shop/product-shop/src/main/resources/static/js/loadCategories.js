

let URL= {
    categories: "/api/findAllProducts",
};


fetch(URL.categories)
    .then((response) => {
        return response.json();
    })
    .then((categories) => {
        categories.forEach((cat, index) => {
            $("#categories").append(`<tr class="row mx-auto">
                   <th class="col-md-2 text-center">${index + 1}</th>
                   <td class="col-md-5 text-center">${cat.name}</td>
                   <td class="col-md-5 text-center">
                       <a href="/categories/edit/${cat.id}" class="btn btn-dark">Edit</a>
                       <a href="/categories/delete/${cat.id}" class="btn btn-dark">Delete</a>
                   </td>
               </tr>`)
        })
    })
    