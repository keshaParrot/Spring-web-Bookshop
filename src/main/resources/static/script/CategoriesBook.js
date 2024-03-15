function filterBooks() {
    const priceSelect = document.getElementById('priceSelect');
    const categorySelect = document.getElementById('categorySelect');
    const ageGroupSelect = document.getElementById('ageGroupSelect');
    const books = document.querySelectorAll('.book');

    const selectedPrice = parseFloat(priceSelect.value);
    const selectedCategory = categorySelect.value;
    const selectedAgeGroup = ageGroupSelect.value;

    books.forEach(function(book) {
        const price = parseFloat(book.getAttribute('th:data-price'));
        const category = book.classList.contains(selectedCategory) || selectedCategory === 'all';
        const ageGroup = book.classList.contains(selectedAgeGroup) || selectedAgeGroup === 'all';
        const priceCondition = selectedPrice === 0 || (price <= selectedPrice && price > 0);

        if (category && ageGroup && priceCondition) {
            book.style.display = 'block';
        } else {
            book.style.display = 'none';
        }
    });
}