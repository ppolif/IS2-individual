function Acceso() {
    return(
        <>
            <aside className="search-wrap">
                <div className="search">
                    <label htmlFor="search">
                        <i className="bi bi-search" style={{fonSize: "1.5rem", color: "cornflowerblue"}}></i>
                        <input type="text" id="search"/>
                    </label>
                </div>

                <div className="user-actions">
                    <button>
                        <a href="#"><i className="bi bi-person-add" style={{fonSize: "1.5rem", color: "cornflowerblue"}}></i></a>
                    </button>
                    <button>
                        <a href="#"><i className="bi bi-person"
                                style={{fonSize: "1.5rem", color: "cornflowerblue"}}></i></a>
                    </button>
                    <button>
                        <a href="#"><i className="bi bi-box-arrow-right"
                                style={{fonSize: "1.5rem", color: "cornflowerblue"}}></i></a>
                    </button>
                </div>
            </aside>
        </>
    )
}

export default Acceso;