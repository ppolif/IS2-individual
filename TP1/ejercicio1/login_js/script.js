const container = document.querySelector('.container')
//const btn = document.getElementById('btn')

//btn.addEventListener('click', () => {
 //   container.classList.toggle('toggle')
//})

const btnSignIn = document.getElementById('btn-sign-in')
const btnSignUp = document.getElementById('btn-sign-up')

btnSignIn.addEventListener('click', () => {
    container.classList.remove('toggle')
})

btnSignUp.addEventListener('click', () => {
    container.classList.toggle('toggle')
})