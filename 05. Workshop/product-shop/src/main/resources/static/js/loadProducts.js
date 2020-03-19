const URL = {
	allProducts: "/api/products",
};

function loadProducts(element) {
    let construct = '';
    let allCheckbox = $("#allRadio").is(':checked');

    if(element.value === 'all' || allCheckbox) {
        getAllProducts();
    } else {
        fetch(URL.allProducts)
        .then((response) => {
            return response.json();
        })
        .then((products) => {
            products.forEach(product => {
                let allCategories = [];
                product.categories.forEach(category=> {
                    allCategories.push(category.name.toLowerCase());
                })
                if(allCategories.includes(element.value)) {
                    construct+= `<div class="product">
                    <div class="text-center">
                        <a href="/products/details/${product.id}">
                            <img src="${product.imageUrl}" class="product-image-home img-thumbnail px-auto" alt="Image not loaded...">
                        </a>
                    </div>
                <h5 class="text-center font-weight-bold mt-3">Name: ${product.name}</h5>
                <h5 class="text-center font-weight-bold">Price: ${product.price}</h5>
                </div>`
                }
            })
            $("#products").empty();
            $("#products").append(construct);
        })
    }
}

function getAllProducts() {
    fetch(URL.allProducts)
        .then((response) => {
            return response.json();
        })
        .then((products)=> { 
             let construct = '';
             products.forEach(product => {
                    construct+= `<div class="product">
                    <div class="text-center">
                        <a href="/products/details/${product.id}">
                            <img src="${product.imageUrl}" class="product-image-home img-thumbnail px-auto" alt="Image not loaded...">
                        </a>
                    </div>
                <h5 class="text-center font-weight-bold mt-3">Name: ${product.name}</h5>
                <h5 class="text-center font-weight-bold">Price: ${product.price}</h5>
                </div>`
                
            })
            $("#products").empty();
            $("#products").append(construct);
        })
       
}