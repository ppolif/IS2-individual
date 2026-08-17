import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { collection, getDocs, getDoc, deleteDoc, doc } from "firebase/firestore";
import { db } from "../firebaseConfig/firebase";
import { MySwal } from '../helpers/alerts';



export function Show() {
    //1- config hooks
    const [products, setProducts] = useState([])

    //2- referenciar db firestore
    const productsCollection = collection(db, "products")

    //3- funcion para mostrar todos los docs
    const getProducts = async() => {
        const data = await getDocs(productsCollection)
        
        const datosLimpios = data.docs.map((doc) => ({...doc.data(), id:doc.id}))

        console.log(datosLimpios)

        setProducts(datosLimpios)
    }

    //4- funcion para eliminar doc
    const deleteProduct = async (id) => {
        const productDoc = doc(db, "products", id)

        await deleteDoc(productDoc)
        getProducts()
    }

    //5- funcion confirmacion sweetalert2
    const confirmDelete = (id) => {
        MySwal.fire({
            title: "Está seguro?",
            text: "Esta acción no puede revertirse!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Si, borrar!"
        }).then((result) => {
            if (result.isConfirmed) {
                deleteProduct(id)
                MySwal.fire({
                    title: "Borrado!",
                    text: "Archivo borrado.",
                    icon: "success"
                });
            }
        });
    }

    //6- use effect
    useEffect(() => {
        getProducts();
    }, []); 

    //
    return (
        <>
            <div className="container">
                <div className="row">
                    <div className="col">
                        <div className="d-grid gap-2">
                            <Link to={"./create"} className="btn btn-secondary mt-2 mb-2">Create</Link>
                        </div>

                        <table className="table table-dark table-hover">
                            <thead>
                                <tr>
                                    <th>Description</th>
                                    <th>Stock</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>

                            <tbody>
                                {products.map((prod) => (

                                    <tr key={prod.id}>
                                        <td>{prod.description}</td>
                                        <td>{prod.stock}</td>
                                        <td>
                                            <Link to={`./edit/${prod.id}`} className="btn btn-light"><i className="fa-regular fa-pen-to-square"></i></Link>
                                            <button onClick={() => confirmDelete(prod.id)} className="btn btn-danger"><i className="fa-solid fa-trash"></i></button>
                                        </td>
                                    </tr>

                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        
        
        </>
    )
}