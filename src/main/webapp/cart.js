function addToCart(itemId) {
    // Create a form to send the item details via POST to the server
    var form = document.createElement("form");
    form.method = "POST";
    form.action = "AddToCartServlet"; // This is the servlet that will handle adding items

    var itemIdInput = document.createElement("input");
    itemIdInput.type = "hidden";
    itemIdInput.name = "itemId";
    itemIdInput.value = itemId;
    form.appendChild(itemIdInput);

    document.body.appendChild(form);
    form.submit();
}

function removeFromCart(itemId) {
    // Create a form to send the item details via POST to the server for removal
    var form = document.createElement("form");
    form.method = "POST";
    form.action = "RemoveFromCartServlet"; // Servlet to handle removal

    var itemIdInput = document.createElement("input");
    itemIdInput.type = "hidden";
    itemIdInput.name = "itemId";
    itemIdInput.value = itemId;
    form.appendChild(itemIdInput);

    document.body.appendChild(form);
    form.submit();
}
