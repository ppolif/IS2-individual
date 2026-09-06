import { ContactCard } from './ContactCard';

export const ContactList = ({ contacts, getContactId }) => {
  const deleteContactHandler = (id) => {
    getContactId(id);
  };

  const renderContactList = contacts.map((contact) => {
    return (
      <ContactCard
        contact={contact}
        clickHandler={deleteContactHandler} 
        key={contact.id}
      />
    );
  });

  return (
    <div className="container mt-5">
      <h3 className="mb-3 text-secondary">Contact List</h3>
      <div className="list-group shadow-sm">
        {renderContactList}
      </div>
    </div>
  );
};
