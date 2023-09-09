import Link from 'next/link'; 
import '@/styles/user-search.css'
const UserSearchOption = ({ firstName, lastName, year, category, country }) => {
  const userSearchOptionStyle = {
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'space-between',
    border: '1px solid #ccc',
    padding: '10px',
    margin: '1rem',
    borderRadius: '10px',
    boxShadow: '0 2px 4px rgba(0, 0, 0, 0.2)',
    transition: 'transform 0.2s ease, background-color 0.2s ease'
  };

  const childDivStyle = {
    flex: '1',
    padding: '5px',
    margin: '5px',
    display: 'flex',
  };

  const biggerChildStyle = {
    flex: '2',
    padding: '5px',
    paddingLeft: '4rem',
  };

  const divStyle = {
    padding: '5px',
    margin: '5px',
    flex: '1',
  };

  const spanStyle = {
    display: 'block',
  };

  const boldBiggerStyle = {
    fontWeight: 'bold',
    fontSize: '1.4rem',
  };
  const boldBiggerNameStyle = {
    fontWeight: 'bold',
    fontSize: '1.5rem',
    marginTop: '0.7rem'
  };

  const graySmallStyle = {
    marginTop:'0.4rem',
    color: 'gray',
    fontSize: '0.9rem',
  };

  return (
    <Link href="/new-page">
      <div style={userSearchOptionStyle} className="user-search-option">
        <div style={biggerChildStyle} className="user-info">
          <span style={{ ...spanStyle, ...boldBiggerNameStyle }}>
            {firstName} {lastName}
          </span>
          <span style={{ ...spanStyle, ...graySmallStyle }}>
            {country ? country.toUpperCase() : ''} {year}
          </span>

        </div>
        <div className="user-extra-info" style={childDivStyle}>
          <div className="user-category" style={divStyle}>
            <span style={{ ...spanStyle, ...boldBiggerStyle }}>
              {category}
            </span>
          </div>
          <div className="user-country" style={divStyle}>
            <span style={{ ...spanStyle, ...boldBiggerStyle }}>
              {country}
            </span>
          </div>
        </div>
      </div>
    </Link>
  );
};

export default UserSearchOption;