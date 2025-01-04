
    const cartItemsList = document.getElementById('cartItemsList');
    const totalElement = document.getElementById('totalElement');

    document.addEventListener('click', function (event) {
    if (!event.target.closest('#cartItemsList')) return;
    if (event.target.classList.contains('increase-quantity')) {
    increaseQuantity(event);
} else if (event.target.classList.contains('decrease-quantity')) {
    decreaseQuantity(event);
} else if (event.target.classList.contains('remove-item')) {
    removeItem(event);
}
});
    function increaseQuantity(event) {
    event.preventDefault();
    const index = Array.from(cartItemsList.children).indexOf(event.target.closest('li'));
    const quantityInput = cartItemsList.children[index].querySelector('input[type="number"]');
    const hiddenQuantityInput = cartItemsList.children[index].querySelector('input[type="hidden"][name^="itemQuantity"]');
    let newQuantity = parseInt(quantityInput.value) + 1;
    if (!isNaN(newQuantity)) {
    quantityInput.value = newQuantity;
    hiddenQuantityInput.value = newQuantity;
    updateTotal();
}
}

    function decreaseQuantity(event) {
    event.preventDefault();
    const index = Array.from(cartItemsList.children).indexOf(event.target.closest('li'));
    const quantityInput = cartItemsList.children[index].querySelector('input[type="number"]');
    const hiddenQuantityInput = cartItemsList.children[index].querySelector('input[type="hidden"][name^="itemQuantity"]');
    const currentValue = parseInt(quantityInput.value);
    if (currentValue > 1) {
    let newQuantity = currentValue - 1;
    if (!isNaN(newQuantity)) {
    quantityInput.value = newQuantity;
    hiddenQuantityInput.value = newQuantity;
    updateTotal();
}
}
}

    function removeItem(event) {
    event.preventDefault();
    const itemToRemove = event.target.closest('li');
    itemToRemove.remove();
    updateTotal();
}

    function updateHiddenFields(index) {
        const item = cartItemsList.children[index];
        const nameInput = item.querySelector('input[type="hidden"][name^="itemName"]');
        const descriptionInput = item.querySelector('input[type="hidden"][name^="itemDescription"]');
        const priceInput = item.querySelector('input[type="hidden"][name^="itemPrice"]');
        const quantityInput = item.querySelector('input[type="hidden"][name^="itemQuantity"]');
        const itemDetails = item.querySelector('.item-details');
        const itemName = itemDetails.querySelector('h2').textContent;
        const itemDescription = itemDetails.querySelector('p').textContent;
        const itemPrice = itemDetails.querySelector('.price').textContent.replace('$', '');
        nameInput.value = itemName;
        descriptionInput.value = itemDescription;
        priceInput.value = itemPrice;
        quantityInput.value = item.querySelector('input[type="number"]').value;
    }

    function updateTotal() {
    let total = 0;
    const items = document.querySelectorAll('.cart-items li');
    items.forEach(item => {
    const priceText = item.querySelector('.price').textContent.replace('$', '');
    const quantity = parseInt(item.querySelector('input[type="number"]').value);
    total += priceText * quantity;
});
    totalElement.textContent = `Total: $${total.toFixed(2)}`;
}