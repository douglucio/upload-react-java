import { BrowserRouter, Route, Routes } from "react-router-dom"
import Uploads from "./pages/Uploads"
import FilesList from "./pages/FilesList"


const MyRoutes = () => {
    return(
        <BrowserRouter>
            <Routes>
                <Route path='/' element={<Uploads />} />
                <Route path='/list' element={<FilesList />} />
            </Routes>
        </BrowserRouter>

    )
}
export default MyRoutes
