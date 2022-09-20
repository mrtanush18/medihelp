import "./App.css";
import { BrowserRouter } from "react-router-dom";
import { Routes, Route, Link } from "react-router-dom";
import ListHospitalComponent from "./components/ListHospitalComponent";
import AddHospitalComponent from "./components/AddHospitalComponent";
import HeaderComponent from "./components/HeaderComponent";

function App() {
  return (
    <div>
      <BrowserRouter>
        {/* Parent */}
        <HeaderComponent />
        <Routes>
          <Route index element={<ListHospitalComponent />} />
          <Route path="/" element={<ListHospitalComponent />}></Route>
          <Route path="/hospitals" element={<ListHospitalComponent />}></Route>
          <Route path="*" element={<ListHospitalComponent />}></Route>
          <Route
            path="/add-hospital"
            element={<AddHospitalComponent />}
          ></Route>
          <Route
            path="/edit-hospital/:hospitalId"
            element={<AddHospitalComponent />}
          ></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
