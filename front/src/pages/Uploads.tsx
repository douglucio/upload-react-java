import { useState } from "react";
import { useNavigate } from 'react-router-dom';
import FilesApi from "../services/FilesApi" 

const Uploads = () => {
    const [music, setMusic] = useState('');
    const [artist, setArtist] = useState('');
    const [gender, setGender] = useState('');
    const [fileSelected, setFileSelected] = useState<File>();
    const history = useNavigate();

    // @ts-ignore
    function handleImage(e) {
        const fileList = e.target.files;
        if (!fileList) return;
        setFileSelected(fileList[0]);
        console.log("[add file] :"+fileSelected)
    }
    // @ts-ignore
    async function handleNewFile(e) {
        e.preventDefault();
        try{
            if (music === '' || artist === '' || gender === '' || fileSelected === null ) {
                alert('preencha todos os campos')
            } else {
                const data = new FormData();
                //@ts-ignore
                data.append("file", fileSelected);
                data.append("music", music);
                data.append("artist", artist);
                data.append("gender", gender);
                const response = await FilesApi.post(`api/uploadFile`, data, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }})
                console.log(data);
            }

            

            history("/list");
        }catch(ex) {
            alert('erro ao cadastrar')
        }
    }

    return(
        <div className="container">
            <h1>Upload de Musicas</h1>
            <div className="form-upload">
                <form onSubmit={handleNewFile} encType="multipart/form-data">
                    <label htmlFor="music">Musica: </label>
                    <br/>
                    <input type="text" name="music" placeholder="Nome da Musica" value={music} onChange={e => setMusic(e.target.value)} />
                    <br/>
                    <label htmlFor="artist">Artista: </label>
                    <br/>
                    <input type="text" placeholder="Nome do Artista" value={artist} onChange={e => setArtist(e.target.value)} />
                    <br/>
                    <label htmlFor="gender">Genero: </label>
                    <br/>
                    <input type="text" placeholder="Gênero" value={gender} onChange={e => setGender(e.target.value)} />
                    <br/>
                    <input type="file" accept="audio/*" onChange={handleImage} />
                    <br/>
                    <button className="button" type="submit">Cadastrar</button>
                </form>
            </div>
        </div>
    );
}
export default Uploads;