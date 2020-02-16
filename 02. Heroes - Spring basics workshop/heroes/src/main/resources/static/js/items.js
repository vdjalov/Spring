let result = "";

const url = {
    items: "/api/items"
};


fetch(url.items)
    .then((response) => {
      return response.json();
    })
    .then((myJson) => {
        let column = ``;
        myJson.forEach(item => {
           column= `
               <td>${item.name}</td>
               <td>${item.slot}</td>
               <td>${item.stamina}</td>
               <td>${item.strength}</td>
               <td>${item.attack}</td>
               <td>${item.defence}</td>`;

           column+= item.owned ? `<td></td>` : 
        `<td>
	        <form action="/items/merchant/${item.name}" method="post">
	                <button class="btn btn-info">Buy</button>
	        </form>
        </td>`;
        
           result+=`<tr>${column}</tr>`;
        });
    }).then(() => {
        $('#items-table').append(result);
    }) 

    
       

    