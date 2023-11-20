import { Table, Input, Button,Label } from 'reactstrap';
import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState, useRef} from "react";
import axios from "axios";

const ModifyForm = () => {
    const {num} = useParams();
    const [board, setBoard] = useState({subject:'', content:'', writer:''});
    const [files, setFiles] = useState([]);
    // const [images, setImages] = useState([]);
    const imgBoxRef = useRef();
    const navigate = useNavigate();
    let selectImg = null;

    useEffect(()=>{
        axios.get(`http://localhost:8088/boardmodify/${num}`)
            .then(res=> {
                console.log(res.data);
                setBoard(res.data);
                let fileurl = res.data.fileurl; // 1,2,3
                let filenums = fileurl.split(','); // [1,2,3]
                let filearr = []; //[{type:'i', data:1}, {}, {}]
                for(let filenum of filenums) {
                    filearr.push({type:'i', data:filenum}); // 문자열을 숫자로 넣기 위해 +filenum해준 것
                }
                setFiles([...filearr]);
            })
            .catch(err=>{
                console.log(err);
            })
    }, [num])

    const change = (e) => {
        const name = e.target.name;
        const value = e.target.value;
        setBoard({...board, [name]:value});
    }
    const fileChange = (e) => {
        if(e.target.files.length === 0) return;
        if(selectImg==null) // 파일 추가
            setFiles([...files, {type:'f',data:e.target.files[0]}]);
        else { // 파일 변경
            let id = selectImg.target.id;
            files.splice(id,1, {type:'f', data:e.target.files[0]});
            setFiles([...files]);
        }
    }

    const imageClick = (e) => {
        selectImg = e;
        document.getElementById("file").click();
    }

    const plusClick = (e) => {
        selectImg = null;
        document.getElementById("file").click();
    }
    const submit = (e) => {
        const formData = new FormData();
        formData.append("num", board.num);
        formData.append("subject", board.subject);
        formData.append("content", board.content);
        formData.append("writer", board.writer);
        // formData.append("file", files);
        for(let file of files) {
            // formData.append("fileInfo", file);
            // 여기서 if-else문에서 append하는 이름이 같아야 백엔드에서 배열로 받음
            if(file.type==='i')
                formData.append("file", new Blob(), file.data);
            else
                formData.append("file", file.data);
        }
        console.log(formData);
        axios.post("http://localhost:8088/boardmodify", formData)
            .then(res=>{
                console.log(res);
                let boardNum = res.data;
                navigate(`/detailform/${boardNum}`);
            })
            .catch(err=>{
                console.log(err);
            })
    }
    const deleteClick = (idx) => {
        files.splice(idx, 1);
        setFiles([...files]);
    }
    return (
        <>
            <h5 style={{textAlign:'center', margin:'20px auto'}}>게시판글수정</h5>
            <div style={{margin:'0 auto',width:'600px', border:'1px solid lightgray', borderRadius:'7px', padding:'10px'}}>
                <Table borderless>
                    <tbody>
                    <tr>
                        <td><Label for="writer">글쓴이</Label></td>
                        <td><Input type="text" name="writer" onChange={change}
                                   id="writer" required="required" value={board.writer}/></td>
                    </tr>
                    <tr>
                        <td><Label for="subject">제 목</Label></td>
                        <td><Input name="subject" type="text" onChange={change}
                                   id="subject" required="required" value={board.subject}/></td>
                    </tr>
                    <tr>
                        <td><Label for="content">내 용</Label></td>
                        <td><Input type='textarea' id="content" name="content" onChange={change}
                                   cols="40" rows="15" required="required" value={board.content}/></td>
                    </tr>
                    <tr>
                        <td><Label for="file"> 이미지 파일 첨부 </Label></td>
                        <td>
                            <img src="/cat.jpg" alt="" width="100px" height="100px" ref={imgBoxRef}
                                 onClick={plusClick}/><br/><br/>
                            <Input name="file" type="file" id="file" accept="image/*" onChange={fileChange} hidden/>
                            {
                                files.length!==0 && files.map((file, index) =>
                                <span key={index}>
                                    <div style={{display:'inline-block'}}><button onClick={()=>deleteClick(index)}>X</button><br/>
                                    <img src={file.type==='i'? `http://localhost:8088/img/${file.data}` : URL.createObjectURL(file.data)}
                                         width="100px" height="100px" alt="" style={{marginRight:"10px"}} id={index} onClick={imageClick}/>
                                    </div>
                                    {(index+1)%3===0 ? <><br/><br/></> : ''}
                                </span>
                                )
                            }
                        </td>


                            {/*{images.length!==0 && images.map(num =>*/}
                            {/*    <img key={num} src={`http://localhost:8088/img/${num}`} alt=''*/}
                            {/*         width="100px" style={{marginRight:"10px"}}/>*/}
                            {/*)*/}
                            {/*}*/}

                            {/*{*/}
                            {/*    files.length!==0 &&*/}
                            {/*    files.map((file, index)=>*/}
                            {/*        // <>*/}
                            {/*        //     <img src={URL.createObjectURL(file)} width="100px" height="100px" alt="" style={{marginRight:"10px"}}/>*/}
                            {/*        //     {(index+1)%3===0 ? <><br/><br/></> : ''}*/}
                            {/*        // </>*/}
                            {/*         <span key={index}>*/}

                            {/*        </span>*/}
                            {/*    )*/}
                            {/*}*/}
                        {/*</td>*/}
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <Button color='primary' onClick={submit}>수정</Button>&nbsp;&nbsp;
                        </td>
                    </tr>
                    </tbody>
                </Table>
            </div>
        </>
    )
}
export default ModifyForm;