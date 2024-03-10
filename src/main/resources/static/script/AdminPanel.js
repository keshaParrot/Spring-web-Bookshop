let listOfSections = new Array()

function start() {
    document.body.classList.remove('blur-filter');
    initSectionList()
    
    showCloseAdminPanel()
    showSection(1)
}
function initSectionList() {
    Array.from(document.getElementsByTagName('section')).forEach(section => {
        if (section.id.endsWith("AdminSection")) {
            listOfSections.push(section);
        }
    });
    console.log(listOfSections);
}
function showSection(number) {
    for (let i = 0; i < listOfSections.length; i++) {
        listOfSections[i].hidden = true;
    }
    if (listOfSections[number]) {
        listOfSections[number].hidden = false;
    }
}
function showCloseAdminPanel() {
    if(document.getElementById("adminPanel").style.display != 'block'){
        document.getElementById("adminPanel").style.display = 'block';
        showSection(1)
        document.body.classList.add('blur-filter');
    }
    else{
        document.getElementById("adminPanel").style.display = 'none';
        document.body.classList.remove('blur-filter');
    }
}

