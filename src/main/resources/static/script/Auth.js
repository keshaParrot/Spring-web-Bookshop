main();
function main(){
    document.body.classList.remove('blur-filter');
}
function showCloseresetPasswordForm() {
    if(document.getElementById("resetPasswordForm").style.display != 'block'){
        document.getElementById("resetPasswordForm").style.display = 'block';
        document.getElementById("container").style.display = 'none';
        document.body.classList.add('blur-filter');
    }
    else{
        document.getElementById("resetPasswordForm").style.display = 'none';
        document.getElementById("container").style.display = 'block';
        document.body.classList.remove('blur-filter');
        document.getElementById("resetEmail").value=''
    }
}