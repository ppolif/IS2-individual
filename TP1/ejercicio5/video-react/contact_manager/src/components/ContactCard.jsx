import user from "../images/user.png";

export const ContactCard = ({ contact, clickHandler }) => {
  const { id, name, email } = contact;
  
  return (
    <div className="list-group-item d-flex justify-content-between align-items-center p-3 border-0 border-bottom">
      
      <div className="d-flex align-items-center">
        <img 
          src={user} 
          alt="user" 
          className="rounded-circle shadow-sm" 
          width="45" 
          height="45" 
        />
        <div className="ms-3">
          <h6 className="mb-0 fw-bold text-dark">{name}</h6>
          <small className="text-muted">{email}</small>
        </div>
      </div>
      
      <button 
        className="btn btn-outline-danger btn-sm"
        onClick={() => clickHandler(id)}
        title="Eliminar contacto"
      >
        🗑️
      </button>
      
    </div>
  );
};
