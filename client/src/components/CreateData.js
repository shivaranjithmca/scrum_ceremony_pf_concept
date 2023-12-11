import React, {Component} from 'react'
import $ from "jquery";
import {ADD_SCRUM_DATA} from "../constants/action-types";
import {connect} from "react-redux";

class CreateData extends Component {
    constructor(props) {
        super(props);
        this.state = {
            sameName: false,
            retrospective: {
                name: "",
                summary: "",
                date: "",
                participants: [""],
                feedback: {
                    items: [
                        	{
                        	  name: "",
                        	  body: "",
                              feedbackType: ""
                            }
                    ]
                }
            }
        }
    }

    render() {
        return (<div className="well">
            <form id="restrospective-section">
            <table className="table">
                <tbody>
                <tr>
                    <th>
                        <strong>Retrospective Section</strong>
                    </th>
                </tr>
                <tr>
                    <td style={{width: "30%"}}>
                        Name:
                    </td>
                    <td>
                        <input name="name" style={{width: 240}} type="text"
                            defaultValue={this.state.retrospective["name"]}
                            onChange={(e) => this.handleChange(e, "name")}
                            className="input-medium"
                            data-rule-required="true"
                        />
                        {this.state.sameName &&
                            <p>The Retrospective Name Already Exist</p>
                        }
                    </td>
                </tr>
                <tr>
                    <td style={{width: "30%"}}>
                        Summary:
                    </td>
                    <td>
                        <input name="summary" style={{width: 240}} type="text"
                            defaultValue={this.state.retrospective["summary"]}
                            onChange={(e) => this.handleChange(e, "summary")}
                            className="input-medium"
                            data-rule-required="true"
                        />
                    </td>
                </tr>
                <tr>
                    <td style={{width: "30%"}}>
                        Date:
                    </td>
                    <td>
                        <input name="date" style={{width: 240}} type="date"
                            defaultValue={this.state.retrospective["date"]}
                            onChange={(e) => this.handleChange(e, "date")}
                            className="input-medium"
                            data-rule-required="true"
                        />
                    </td>
                </tr>
                {this.state.retrospective["participants"].map((participant, i) => {
                    return (
                        <tr key={i}>
                            <td style={{width: "30%"}}>
                                Participant {i + 1}:
                            </td>
                            <td>
                                <input name={"participant"+i} style={{width: 240}} type="text"
                                    defaultValue={participant}
                                    onChange={(e) => this.handleChange(e, "participant", i)}
                                    className="input-medium"
                                    data-rule-required="true"
                                />
                                <input id={"participant_button_"+i} style={{margin: 10}}
                                    className="btn btn-primary add-txt" value="+"
                                    type="button" onClick={() => this.handleAddParticipant(i)}/>
                            </td>
                        </tr>
                    )
                })}
                <tr>
                    <td>
                        <a className="btn btn-primary" href="/scrum/home/"
                            style={{color: "#ffffff"}}>
                           Home
                        </a>
                    </td>
                    {!this.state.sameName &&
                        <td>
                            <a className="btn btn-primary" onClick={() => this.handleAddRetrospective()}
                                style={{color: "#ffffff"}}>
                                Add
                            </a>
                        </td>
                    }
                </tr>
                </tbody>
            </table>
            </form>
            </div>)
    }

    handleChange(e, property, index) {
        let value = e.target.value
        let retrospective = this.state.retrospective
        if (value != "") {
            if (property == "name") {
                retrospective["name"] = value
            } else if (property == "summary") {
                retrospective["summary"] = value
            } else if (property == "date") {
                retrospective["date"] = value
            } else if (property == "participant") {
                retrospective["participants"][index] = value
            }

            this.setState({
                retrospective: retrospective,
                sameName: false
            })
        }
    }

    handleAddParticipant(index) {
        if ($("#restrospective-section").valid()) {
            $("#participant_button_"+index).hide()
            let retrospective = this.state.retrospective
            retrospective["participants"].push("")
            this.setState({
                retrospective: retrospective
            })
        }
    }

    handleAddRetrospective() {
        if ($("#restrospective-section").valid()) {
            if(this.props.scrumData["retrospectives"].find((r) => r["name"] == this.state.retrospective["name"])) {
                this.setState({
                    sameName: true
                })
            } else {
                let retrospectives = this.props.scrumData["retrospectives"]
                retrospectives.push(this.state.retrospective)
                this.props.dispatch({type: ADD_SCRUM_DATA, payload: retrospectives})
                window.location.href = "/scrum/view/retrospective"
            }
        }
    }
}

const mapStateToProps = state => {
    return {
        scrumData: state.scrumData
    }
}

export default connect(mapStateToProps)(CreateData)