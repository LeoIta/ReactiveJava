package com.reactiveJavaProject.sec11Sinks;

import com.reactiveJavaProject.courseUtil.Util;
import com.reactiveJavaProject.sec11Sinks.assignment.SlackMember;
import com.reactiveJavaProject.sec11Sinks.assignment.SlackRoom;

public class Lec10SlackDemo {

    public static void main(String[] args) {

        SlackRoom slackRoom = new SlackRoom("Java reactor Course");

        SlackMember sam = new SlackMember("sam");
        SlackMember jake = new SlackMember("jake");
        SlackMember mike = new SlackMember("mike");

        slackRoom.joinRoom(sam);
        slackRoom.joinRoom(jake);

        sam.says("Hi all..");
        Util.sleepSeconds(4);

        jake.says("Hey!");
        sam.says("I simply wanted to say hi..");
        Util.sleepSeconds(4);

        slackRoom.joinRoom(mike);
        mike.says("Hey guys..glad to be here...");

    }
}