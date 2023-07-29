import { FormEvent, useState } from "react";
import FilesApi from "../services/FilesApi";
import { useNavigate } from "react-router-dom";

const Home = () => {
    const [search, setSearch] = useState('');
    const history = useNavigate();
    const [musics, setMusics] = useState<any[]>([]);

    async function getMusics(e: FormEvent<HTMLFormElement>) {
        e.preventDefault();
        try{
            if (search === ''  ) {
                alert('preencha todos os campos')
            } else {
                console.log(search);
                FilesApi.get(`api/files/list/${search}`).then((response) => {
                    setMusics(response.data); 
                });
            }
        }catch(ex) {
            alert('erro ao buscar os dados')
        }
    }

    return(
        <div className="home">
            <h1>Home</h1>
            <form onSubmit={getMusics}>
                <input type="text" name="search" placeholder="buscar por musica, artista ou genero" value={search} onChange={e => setSearch(e.target.value)}/>
                <button type="submit">Buscar</button>
            </form>
            <ul>
                {musics?.map(music => (
                    <li key={music.uuid}>
                        <table>
                            <tr>
                                <td width="250px">{music.music}</td>
                                <td width="250px">{music.artist}</td>
                                <td width="350px"><audio src={`data:${music.type};base64,${music.data}`} controls /></td>
                            </tr>
                        </table>
                    </li>
                ))}
            </ul>
        </div>
    )
}
export default Home;