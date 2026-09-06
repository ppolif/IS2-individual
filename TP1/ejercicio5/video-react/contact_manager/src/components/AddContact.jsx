import { useState } from 'react'; 

export const AddContact = ({ addContactHandler }) => {
  
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");

  const add = (e) => {
    e.preventDefault(); 
    
    if (name === "" || email === "") {
      alert("All the fields are mandatory!");
      return;
    }
    

    addContactHandler({ name, email });
    
    setName("");
    setEmail("");
  };

  return (
    <div className="container mt-4">
      <h2 className="mb-3 text-secondary">Add Contact</h2>
      <form onSubmit={add} className="card p-4 shadow-sm border-0">
        <div className="mb-3">
          <label className="form-label">Name</label>
          <input
            type="text"
            className="form-control"
            name="name"
            placeholder="Name"
            value={name}
            onChange={(e) => setName(e.target.value)} 
          />
        </div>
        <div className="mb-4">
          <label className="form-label">Email</label>
          <input
            type="email"
            className="form-control"
            name="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)} 
          />
        </div>
        <button type="submit" className="btn btn-primary w-100">
          Add
        </button>
      </form>
    </div>
  );
};