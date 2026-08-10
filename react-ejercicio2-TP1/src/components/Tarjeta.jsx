import './Tarjeta.css'

export function Tarjeta({item}) {

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
                <button className="tarjeta-appeals">
                    Appeals to me
                </button>

                <button className="tarjeta-skip">
                    Next
                </button>
            </div>
        </div>
    )

}