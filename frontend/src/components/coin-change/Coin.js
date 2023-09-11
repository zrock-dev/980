function Coin({ value, key, type, amount }) {
  return (
    <div className="coin">
      <div className="coinImg" keyId={key}>
        {value}
      </div>
      <div className="coinRepeated">{amount && `x ${amount}`}</div>
    </div>
  );
}

export default Coin;
