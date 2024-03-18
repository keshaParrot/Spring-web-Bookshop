let rangeOne = document.querySelector('input[name="inputMinPrice"]'),
    rangeTwo = document.querySelector('input[name="inputMaxPrice"]'),
    outputOne = document.querySelector('.outputOne'),
    outputTwo = document.querySelector('.outputTwo'),
    inclRange = document.querySelector('.incl-range');

let updateView = function () {
    let min = parseInt(rangeOne.value);
    let max = parseInt(rangeTwo.value);

    if (min > max) {
        let temp = min;
        min = max;
        max = temp;
    }

    outputOne.innerHTML = min;
    outputOne.style.left = (min / this.getAttribute('max')) * 100 + '%';
    outputTwo.style.left = (max / this.getAttribute('max')) * 100 + '%';
    outputTwo.innerHTML = max;

    inclRange.style.width = ((max - min) / this.getAttribute('max')) * 100 + '%';
    inclRange.style.left = (min / this.getAttribute('max')) * 100 + '%';
};

document.addEventListener('DOMContentLoaded', function () {
    updateView.call(rangeOne);
    updateView.call(rangeTwo);
    document.querySelectorAll('input[type="range"]').forEach(function (input) {
        input.addEventListener('mouseup', function() {
            this.blur();
        });
        input.addEventListener('mousedown', updateView);
        input.addEventListener('input', updateView);
    });
});
console.log("min " + parseInt(rangeOne.value) + " max " +parseInt(rangeTwo.value))
console.log("min o " + parseInt(outputOne.value) + " max o " +parseInt(outputTwo.value))

function validateForm(){
    let min = parseInt(rangeOne.value);
    let max = parseInt(rangeTwo.value);

    if (min > max) {
        let temp = rangeOne.value;
        rangeOne.value = rangeTwo.value;
        rangeTwo.value = temp;
    }
}
