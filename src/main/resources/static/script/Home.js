


function main(){

}
function searchBook(){
    let searchBookInputFieldVariable = document.getElementById("searchBookInputField");
    let searchBookButtonVariable = document.getElementById("searchBookButton");

    if(searchBookInputFieldVariable.value === ""){
        searchBookInputFieldVariable.placeholder = "the field cannot be empty";
    } else {
        if(searchBookButtonVariable.innerHTML === "Clear"){
            searchBookInputFieldVariable.value = "";
            searchBookButtonVariable.innerHTML = "Search";
            clearSearchBook()
        } else {
            searchBookButtonVariable.innerHTML = "Clear";
            searchBookOnReporithory(searchBookInputFieldVariable.value);
        }
    }
}
function clearSearchBook(){

}
function searchBookOnReporithory(argh){

}