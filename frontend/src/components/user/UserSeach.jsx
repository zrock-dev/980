const UserSearchOption = ({ firstName, lastName, year, category, country }) => {
  return (
    <div className="user-search-option">
      <div className="user-info">
          <span>{firstName} {lastName}</span>
          <span> {country}</span>
          <span> {year}</span>
      </div>
      <div className="user-category">
          <span> {category}</span>
      </div>
      <div className="user-country">
          <span> {country}</span>
      </div>
    </div>
  );
};

export default UserSearchOption;
