
function changeEditMode() {
    let listOfInputs = document.getElementsByTagName("input");
    let saveButton = document.getElementById("saveButton");
    let editButton = document.getElementById("editButton");
    for (let i = 0; i < listOfInputs.length; i++) {
        listOfInputs[i].readOnly = false;
    }
    saveButton.hidden = false;
    editButton.hidden = true;
}

