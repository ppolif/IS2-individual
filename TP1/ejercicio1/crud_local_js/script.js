let listaEmpleados = []

const empleado = {
    id: '',
    nombre: '',
    puesto: ''
}

let editando = false

const formulario = document.querySelector('#formulario')
const nombreInput = document.querySelector('#nombre')
const puestoInput = document.querySelector('#puesto')
const btnAgregar = document.querySelector('#btn-agregar')

formulario.addEventListener('submit', validarForm)

function validarForm(e) {
    e.preventDefault()

    if (nombreInput.value === '' || puestoInput.value === '') {
        alert('Todos los campos son obligatorios')
        return
    }

    if (editando) {
        editarEmpleado()
        editando = false
    } else {
        empleado.id = Date.now()
        empleado.nombre = nombreInput.value
        empleado.puesto = puestoInput.value

        agregarEmpleado()
    }
}

function agregarEmpleado() {
    listaEmpleados.push({...empleado})

    mostrarEmpleados()

    formulario.reset()

    limpiarObjeto()
}

function limpiarObjeto() {
    empleado.id = ''
    empleado.puesto = ''
    empleado.nombre = ''
}

function mostrarEmpleados() {
    limpiarHtml()

    const divEmpleados = document.querySelector('.div-empleados')

    listaEmpleados.forEach( empleado => {
        const {id, nombre, puesto} = empleado

        const parrafo = document.createElement('p')
        parrafo.textContent = `${id} - ${nombre} - ${puesto} -`
        parrafo.dataset.id = id;

        const editarBoton = document.createElement('button')
        editarBoton.onclick = () => cargarEmpleado(empleado)
        editarBoton.textContent = 'Editar'
        editarBoton.classList.add('btn', 'btn-editar')
        parrafo.append(editarBoton)

        const eliminarBoton = document.createElement('button')
        eliminarBoton.onclick = () => eliminarEmpleado(id)
        eliminarBoton.textContent = 'Eliminar'
        eliminarBoton.classList.add('btn', 'btn-eliminar')
        parrafo.append(eliminarBoton)

        const hr = document.createElement('hr')
        divEmpleados.appendChild(parrafo)
        divEmpleados.appendChild(hr)
    })
}

function limpiarHtml() {
    const divEmpleados = document.querySelector('.div-empleados')
    
    while(divEmpleados.firstChild) {
        divEmpleados.removeChild(divEmpleados.firstChild)
    }
}

function cargarEmpleado(emp) {
    const {id, nombre, puesto} = emp

    nombreInput.value = nombre
    puestoInput.value = puesto

    empleado.id = id

    formulario.querySelector('button[type="submit"]').textContent = 'Actualizar'

    editando = true
}

function editarEmpleado() {
    empleado.nombre = nombreInput.value
    empleado.puesto = puestoInput.value

    listaEmpleados.map( elemEmpleado => {

        if (elemEmpleado.id === empleado.id) {
            elemEmpleado.id = empleado.id
            elemEmpleado.nombre = empleado.nombre
            elemEmpleado.puesto = empleado.puesto
        }
    })

    limpiarHtml()
    mostrarEmpleados()

    formulario.reset()

    formulario.querySelector('button[type="submit"]').textContent = 'Agregar'

    editando = false
}

function eliminarEmpleado(id) {
    listaEmpleados = listaEmpleados.filter(elemEmpleado => elemEmpleado.id !== id)

    limpiarHtml()
    mostrarEmpleados()
}

