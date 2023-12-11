import React, {Component} from 'react'
import $ from "jquery";
import {ADD_SCRUM_DATA} from "../constants/action-types";
import {connect} from "react-redux";

class ViewData extends Component {
    constructor(props) {
        super(props);
        this.state = {
            showAddItem: true,
            retrospective: {}
        }
    }

    render() {
        let retrospective = this.state.retrospective
        return (<div className="well">
        {this.state.retrospective["name"] == undefined ?
            <table className="table">
                <tbody>
                {this.props.scrumData["retrospectives"].map((r, i) => {
                    if(r["name"] != null && r["name"] != "") {
                        return (<tr key={i}>
                            <td>
                                <a className="btn btn-primary" onClick={() => this.handleView(r)}
                                    style={{color: "#ffffff"}}>
                                    View {r["name"]}
                                </a>
                            </td>
                        </tr>)
                    }
                })}
                </tbody>
            </table>
        :
        <form id="restrospective-section-view">
            <table className="table">
                <tbody>
                <tr>
                    <th>
                        <strong>{retrospective["name"]}</strong>
                    </th>
                </tr>
                <tr>
                    <td style={{width: "30%"}}>
                        Summary:
                    </td>
                    <td>
                        {retrospective["summary"]}
                    </td>
                </tr>
                <tr>
                    <td style={{width: "30%"}}>
                        Date:
                    </td>
                    <td>
                        {retrospective["date"]}
                    </td>
                </tr>
                <tr>
                    <td style={{width: "30%"}}>
                        Participants:
                    </td>
                    <td>
                        {retrospective["participants"].join(', ')}
                    </td>
                </tr>
                {retrospective["feedback"]["items"].map((item, i) => {
                    return (<><tr>
                                <td style={{width: "30%"}}>
                                    Item {i + 1}:
                                </td>
                            </tr>
                            <tr>
                                <td style={{width: "30%"}}>
                                    Name:
                                </td>
                                <td>
                                    <select name={"item_name_" + i}
                                        id={"item_name_" + i}
                                        value={item["name"]}
                                        onChange={(e) => this.handleChange(e, "itemName", i)}>
                                        <option value=""></option>
                                        {retrospective["participants"].map((participant, j) => {
                                            return (
                                                <option key={j} value={participant}>{participant}</option>)
                                        })
                                        }
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td style={{width: "30%"}}>
                                    Body:
                                </td>
                                <td>
                                    <input name={"item_body_" + i} style={{width: 240}} type="text"
                                        defaultValue={item["body"]}
                                        onChange={(e) => this.handleChange(e, "itemBody", i)}
                                        className="input-medium"
                                        data-rule-required="true"
                                    />
                                </td>
                            </tr>
                            <tr>
                                <td style={{width: "30%"}}>
                                    FeedbackType:
                                </td>
                                <td>
                                    <select name={"item_type_" + i}
                                        id={"item_type_" + i}
                                        value={item["feedbackType"]}
                                        onChange={(e) => this.handleChange(e, "itemType", i)}>
                                        <option value=""></option>
                                        <option value="praise">praise</option>
                                        <option value="positive">positive</option>
                                        <option value="negative">negative</option>
                                        <option value="idea">idea</option>
                                    </select>
                                </td>
                            </tr>
                    </>)
                })
                }
                <tr>
                    <td>
                        <a className="btn btn-primary" onClick={() => this.handleHomeView()}
                            style={{color: "#ffffff"}}>
                            Home
                        </a>
                    </td>
                    {this.state.showAddItem &&
                        <td>
                            <a className="btn btn-primary" onClick={() => this.handleAddItem()}
                                style={{color: "#ffffff"}}>
                                Add Item
                            </a>
                        </td>
                    }
                </tr>
                </tbody>
            </table>
        </form>
        }
        </div>)
    }

    handleView(retrospective) {
        this.setState({
            retrospective: retrospective,
            showAddItem: retrospective["participants"].length != retrospective["feedback"]["items"].length
        })
    }

    handleHomeView() {
        let retrospectives = this.props.scrumData["retrospectives"]
        let index = retrospectives.findIndex(r => r["name"] == this.state.retrospective["name"]);
        retrospectives[index] = this.state.retrospective
        this.props.dispatch({type: ADD_SCRUM_DATA, payload: retrospectives})
        window.location.href = "/scrum"
    }

    handleChange(e, property, index) {
        let value = e.target.value
        let retrospective = this.state.retrospective
        if (value != "") {
            if (property == "itemName") {
                retrospective["feedback"]["items"][index]["name"] = value
            } else if (property == "itemBody") {
                retrospective["feedback"]["items"][index]["body"] = value
            } else if (property == "itemType") {
                retrospective["feedback"]["items"][index]["feedbackType"] = value
            }

            this.setState({
                retrospective: retrospective
            })
        }
    }

    handleAddItem() {
        if ($("#restrospective-section-view").valid()) {
            let retrospective = this.state.retrospective
            retrospective["feedback"]["items"].push({
                "name": "","body": "","feedbackType": ""
            })
            this.setState({
                retrospective: retrospective,
                showAddItem: retrospective["participants"].length != retrospective["feedback"]["items"].length
            })
        }
    }
}

const mapStateToProps = state => {
    return {
        scrumData: state.scrumData
    }
}

export default connect(mapStateToProps)(ViewData)