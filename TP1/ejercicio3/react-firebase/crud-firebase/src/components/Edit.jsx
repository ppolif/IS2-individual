import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { getDoc, updateDoc, doc } from "firebase/firestore";
import { db } from "../firebaseConfig/firebase";

export function Edit() {
    const [description, setDescription] = useState('')
    const [stock, setStock] = useState(0)

    const navigate = useNavigate()
    const {id} = useParams()

    const update = async (e) => {
        e.preventDefault()
        const prod = doc(db, "products", id)
        const data = {description:description, stock:stock}
        await updateDoc(prod, data)
        navigate('/')
    }

    const getProdById = async (id) => {
        const prod = await getDoc(doc(db, "products", id))

        if (prod.exists()) {
            setDescription(prod.data().description)
            setStock(prod.data().stock)
        } else {
            console.log("El producto no existe")
        }
    }

    useEffect( () => {
        console.log("El ID recibido en la URL es:", id)
        getProdById(id)
    }, [] )

    return(
        <>
            <div className="container">
                <div className="row">
                    <div className="col">

                        <h1>Editar producto</h1>

                        <form onSubmit={update}>
                            <div className="mb-3">
                                <label className="form-label">Descripción</label>
                                <input 
                                    value={description}
                                    onChange={(e) => setDescription(e.target.value)}
                                    type="text" 
                                    className="form-control" 
                                />
                            </div>

                            <div className="mb-3">
                                <label className="form-label">Stock</label>
                                <input 
                                    value={stock}
                                    onChange={(e) => setStock(e.target.value)}
                                    type="number" 
                                    className="form-control" 
                                />
                            </div>

                            <button type="submit" className="btn btn-primary">Update</button>
                        </form>

                    </div>
                </div>
            </div>
        </>
    )
}