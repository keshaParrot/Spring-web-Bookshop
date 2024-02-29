listOfSection = new Array();

function start() {
    document.body.classList.remove('blur-filter');
}
function initSectionList() {

}
function showSection() {

}
function showCloseAdminPanel() {
    if(document.getElementById("adminPanel").style.display != 'block'){
        document.getElementById("adminPanel").style.display = 'block';
        document.body.classList.add('blur-filter');
    }
    else{
        document.getElementById("adminPanel").style.display = 'none';
        document.body.classList.remove('blur-filter');
    }
}