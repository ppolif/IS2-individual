import './Tarjeta.css'
import { useState } from 'react'

export function Tarjeta({item, appeals, manejoAppeals, manejoNext}) {

    const text = appeals ? 'Appealing!' : 'Appeals to me' 
    const claseBoton = appeals ? 'appealing' : 'notAppealing'


    return (
        <div className="tarjeta">
            <header className="tarjeta-header">

                <h1 className="tarjeta-title">{item.titulo}</h1>

                {item.subtitulo && (
                    <h2 className="tarjeta-autor">{item.subtitulo}</h2>
                )}

                <p className="tarjeta-categorie">{item.categoria}</p>
                
            </header>

            <div className="tarjeta-cuerpo">
                <img className="tarjeta-imagen" src={item.imagenUrl} alt=""/>

                {item.descripcion && (
                    <p> {item.descripcion} </p>
                )}
            </div>

            <div className="tarjeta-botones">
                <button className={`tarjeta-appeals ${claseBoton}`} onClick={manejoAppeals}>
                    {text}
                </button>

                <button className="tarjeta-skip" onClick={manejoNext}>
                    Next
                </button>
            </div>
        </div>
    )

}