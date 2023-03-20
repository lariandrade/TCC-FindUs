let senha = document.getElementById('userSenha');
let senhaC = document.getElementById('confirmarSenha');

function validarSenha() {
  if (userSenha.value != confirmarSenha.value) {
    confirmarSenha.setCustomValidity("Senhas diferentes!");
    confirmarSenha.reportValidity();
    return false;
  } else {
    confirmarSenha.setCustomValidity("");
    return true;
  }
}

confirmarSenha.addEventListener('input', validarSenha);