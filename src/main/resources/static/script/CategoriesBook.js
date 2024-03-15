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


let thumbsize = 14;
function draw(slider,splitvalue) {

    /* set function lets */
    let min = slider.querySelector('.min');
    let max = slider.querySelector('.max');
    let lower = slider.querySelector('.lower');
    let upper = slider.querySelector('.upper');
    let legend = slider.querySelector('.legend');
    let thumbsize = parseInt(slider.getAttribute('data-thumbsize'));
    let rangewidth = parseInt(slider.getAttribute('data-rangewidth'));
    let rangemin = parseInt(slider.getAttribute('data-rangemin'));
    let rangemax = parseInt(slider.getAttribute('data-rangemax'));

    /* set min and max attributes */
    min.setAttribute('max',splitvalue);
    max.setAttribute('min',splitvalue);

    /* set css */
    min.style.width = parseInt(thumbsize + ((splitvalue - rangemin)/(rangemax - rangemin))*(rangewidth - (2*thumbsize)))+'px';
    max.style.width = parseInt(thumbsize + ((rangemax - splitvalue)/(rangemax - rangemin))*(rangewidth - (2*thumbsize)))+'px';
    min.style.left = '0px';
    max.style.left = parseInt(min.style.width)+'px';
    min.style.top = lower.offsetHeight+'px';
    max.style.top = lower.offsetHeight+'px';
    legend.style.marginTop = min.offsetHeight+'px';
    slider.style.height = (lower.offsetHeight + min.offsetHeight + legend.offsetHeight)+'px';

    /* correct for 1 off at the end */
    if(max.value>(rangemax - 1)) max.setAttribute('data-value',rangemax);

    /* write value and labels */
    max.value = max.getAttribute('data-value');
    min.value = min.getAttribute('data-value');
    lower.innerHTML = min.getAttribute('data-value');
    upper.innerHTML = max.getAttribute('data-value');

}

function init(slider) {
    let min = slider.querySelector('.min');
    let max = slider.querySelector('.max');
    let rangemin = parseInt(min.getAttribute('min'));
    let rangemax = parseInt(max.getAttribute('max'));
    let avgvalue = (rangemin + rangemax)/2;
    let legendnum = slider.getAttribute('data-legendnum');

    min.setAttribute('data-value',rangemin);
    max.setAttribute('data-value',rangemax);

    slider.setAttribute('data-rangemin',rangemin);
    slider.setAttribute('data-rangemax',rangemax);
    slider.setAttribute('data-thumbsize',thumbsize);
    slider.setAttribute('data-rangewidth',slider.offsetWidth);

    let lower = document.createElement('span');
    let upper = document.createElement('span');
    lower.classList.add('lower','value');
    upper.classList.add('upper','value');
    lower.appendChild(document.createTextNode(rangemin));
    upper.appendChild(document.createTextNode(rangemax));
    slider.insertBefore(lower,min.previousElementSibling);
    slider.insertBefore(upper,min.previousElementSibling);

    let legend = document.createElement('div');
    legend.classList.add('legend');
    let legendvalues = [];
    for (let i = 0; i < legendnum; i++) {
        legendvalues[i] = document.createElement('div');
        let val = Math.round(rangemin+(i/(legendnum-1))*(rangemax - rangemin));
        legendvalues[i].appendChild(document.createTextNode(val));
        legend.appendChild(legendvalues[i]);

    }
    slider.appendChild(legend);

    draw(slider,avgvalue);

    min.addEventListener("input", function() {update(min);});
    max.addEventListener("input", function() {update(max);});
}

function update(el){
    let slider = el.parentElement;
    let min = slider.querySelector('#min');
    let max = slider.querySelector('#max');
    let minvalue = Math.floor(min.value);
    let maxvalue = Math.floor(max.value);

    /* set inactive values before draw */
    min.setAttribute('data-value',minvalue);
    max.setAttribute('data-value',maxvalue);

    let avgvalue = (minvalue + maxvalue)/2;

    /* draw */
    draw(slider,avgvalue);
}

let sliders = document.querySelectorAll('.min-max-slider');
sliders.forEach( function(slider) {
    init(slider);
});