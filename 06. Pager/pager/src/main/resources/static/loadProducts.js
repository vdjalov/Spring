
const currentURL =  window.location.href;
const parsedURL = currentURL.match("page=[0-9]+");
let URL = "/api/products";
if(parsedURL != null) {
    URL+= `?${parsedURL[0]}`;
}

fetch(URL)
    .then((response) => {
    	return response.json();
    })
    .then((data) => {
     
        let h1 = $(`<h1></h1>`);
        let pagingSpan = $(`<span></span>`);

        data.content.forEach(product => {
           h1.append(`${product.name}`).append(`<br/>`);
        });

        let counter = 1;
        let currentPage = +data.number;
        pagingSpan.append(`<a href="http://localhost:8080/home?page=${currentPage}">${currentPage + 1}</a>`).append(` | `);
        for(let i = data.number; i < data.totalPages && counter <= 2; i++) {
            let previousPage = +currentPage - counter;
            let nextPage = +currentPage + counter;
            console.log(previousPage);
            if(previousPage >= 0) {
                pagingSpan.prepend(` | `).prepend(`<a href="http://localhost:8080/home?page=${previousPage}">${previousPage + 1}</a>`);
            }

            if(nextPage < data.totalPages) {
                pagingSpan.append(`<a href="http://localhost:8080/home?page=${nextPage}">${nextPage + 1}</a>`).append(` | `);
            }

            if(currentPage == data.totalPages - 1 && counter == 1) {
                pagingSpan.prepend(` | `).prepend(`<a href="http://localhost:8080/home?page=${previousPage - 1}">${previousPage}</a>`);
            }
            counter++; // Perfect so that it always returns 5 pages or less if there are less than 5 pages 
        }
       
        $(`#myProducts`).append(h1);
        $(`#paging`).append(pagingSpan);
    })

