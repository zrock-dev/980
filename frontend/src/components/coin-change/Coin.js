import Image from "next/image";

function Coin({ value, type, amount }) {
  const src = value < 1 ? `/${value}cent${type}.png` : `/${value}${type}.png`;

  return (
    <div className="coin">
      <Image src={src} width={210} height={120} alt={`${value} ${type}`} />
      {amount && <div className="coinRepeated">x {amount}</div>}
    </div>
  );
}

export default Coin;
