import React, { useState } from "react";

const SelectSeats = ({passengers ,  isOpen, onClose}) => {
  if (!isOpen) return null;

  
  const columns = ["A", "B", "C", "D", "E", "F"];
  const rows = 30;
  
  const seats = [];
  for (let i = 0; i < rows; i++) {
    const row = [];
    for (let j = 0; j < columns.length; j++) {
      row.push({
        id: `${i + 1}${columns[j]}`, 
        occupied: false,
      });
    }
    seats.push(row);
  }
  const bookedSeats = localStorage.getItem('bookedSeats') || "";
  console.log("booked seats" , bookedSeats);
  const [seatData] = useState(seats);
  const [selectedSeatsStr, setSelectedSeatsStr] = useState("");
  const [incorrectSeat , setIncorrectSeat] = useState(false);
  const bookedArray = bookedSeats ? bookedSeats.split(",") : [] ;
  const handleClick = (seatId) => {
    let updatedArray;
    if (seatsArray.includes(seatId)) {
      // Remove seat if already selected
      updatedArray = seatsArray.filter((id) => id !== seatId);
    } else {
      // Add seat to selection
      updatedArray = [...seatsArray, seatId];
    }

    setSelectedSeatsStr(updatedArray.join(","));
  };
  
  const ConfirmSeats = () => {
    if(seatsArray.length == passengers ) {
        const merged = [...bookedArray , ...seatsArray];
        const mergedstr = merged.join(",");
        localStorage.setItem('selectedSeats', mergedstr);


        onClose();


    }
    else{
        setIncorrectSeat(true);


    }
  }
  const seatsArray = selectedSeatsStr ? selectedSeatsStr.split(",") : [];
  return (
    <div
      style={{
        maxHeight: "80vh",
        maxWidth: "600px",
        overflowY: "auto",
        overflowX: "auto",
        padding: "10px",
        border: "1px solid #ccc",
        borderRadius: "8px",
        backgroundColor: "#f9f9f9",
      }}
    >
      <table
        style={{
          borderCollapse: "collapse",
          tableLayout: "fixed",
          width: "100%",
          fontFamily: "monospace",
        }}
      >
        <thead>
          <tr>
            <th
              style={{
                width: "40px",
                textAlign: "center",
                padding: "4px",
                borderBottom: "1px solid #ccc",
              }}
            >
              Row
            </th>
            {columns.map((col) => (
              <th
                key={col}
                style={{
                  width: "50px",
                  textAlign: "center",
                  padding: "4px",
                  borderBottom: "1px solid #ccc",
                  whiteSpace: "nowrap",
                  overflow: "hidden",
                  textOverflow: "ellipsis",
                }}
              >
                {col}
              </th>
            ))}
          </tr>
        </thead>
        <tbody>
          {seatData.map((row, rowIdx) => (
            <tr key={rowIdx}>
              <td
                style={{
                  textAlign: "center",
                  fontWeight: "bold",
                  padding: "4px",
                  borderBottom: "1px solid #eee",
                }}
              >
                {rowIdx + 1}
              </td>
              {row.map((seat) => (
                <td
                  key={seat.id}
                  style={{
                    padding: "4px",
                    textAlign: "center",
                    borderBottom: "1px solid #eee",
                    whiteSpace: "nowrap",
                    overflow: "hidden",
                    textOverflow: "ellipsis",
                  }}
                >
                  {!bookedArray.includes(seat.id) && <button
                    onClick={() => handleClick(seat.id)}
                    style={{
                      width: "45px",
                      height: "35px",
                      fontSize: "12px",
                      fontFamily: "monospace",
                      backgroundColor: seatsArray.includes(seat.id)
                        ? "green"
                        : "lightgray",
                      color: "white",
                      border: "none",
                      borderRadius: "4px",
                      cursor: "pointer",
                    }}
                    title={seat.id} // tooltip for full seat id
                  >
                    {seat.id}
                  </button>}
                  {bookedArray.includes(seat.id) &&
                     <div style={{
                      width: "45px",
                      height: "35px",
                      fontSize: "12px",
                      fontFamily: "monospace",
                      backgroundColor:"red",
                      color: "white",
                      border: "none",
                      borderRadius: "4px",
                      cursor: "pointer",
                    }}>{seat.id}</div>}
                </td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
      <button onClick={() => ConfirmSeats()}>Confirm seats</button>
      {incorrectSeat && (<div>Select the correct number of seats </div>)}
    </div>
  );
};

export default SelectSeats;
