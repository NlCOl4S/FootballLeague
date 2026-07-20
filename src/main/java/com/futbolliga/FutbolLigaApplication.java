package com.futbolliga;

import com.futbolliga.entity.Team;
import com.futbolliga.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class FutbolLigaApplication implements CommandLineRunner {

    private final TeamRepository teamRepository;

    public static void main(String[] args) {
        SpringApplication.run(FutbolLigaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("======================================================");
        System.out.println("🤖 FOOTBALL LEAGUE API INITIALIZED SUCCESSFULLY");
        System.out.println("======================================================");

        System.out.println("🔍 Fetching registered teams from database...");
        List<Team> teams = teamRepository.findAll();

        if (teams.isEmpty()) {
            System.out.println("ℹ️ No teams found in database. Ready for Talend seeding!");
        } else {
            System.out.println("👌 Active league teams:");
            teams.stream().map(Team::getName).forEach(System.out::println);
        }

        System.out.println("======================================================");
    }
}
