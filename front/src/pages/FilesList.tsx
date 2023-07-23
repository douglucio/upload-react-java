import { useEffect, useState } from "react";
import FilesApi from "../services/FilesApi";

const FilesList = () => {
    const [files, setFiles] = useState<any[]>([]);

    useEffect(() => {
        FilesApi.get(`api/files/list`).then((response) => {
            setFiles(response.data); 
        });
    }, []);

    //if (loading || !data) return "Loading...";

    return(
        <div className="list">
            <h1>Lista de Arquivos</h1>
            <ul>
                {files?.map(file => (
                    <li key={file.uuid}>
                        <table>
                            <tr>
                                <td width="250px">{file.music}</td>
                                <td width="250px">{file.artist}</td>
                                <td width="350px"><audio src={`data:${file.type};base64,${file.data}`} controls /></td>
                            </tr>
                        </table>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default FilesList;