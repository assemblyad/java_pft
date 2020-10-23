package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("6f3983c88c7b98ab1704a1ae8687ebea0711afd5");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("assemblyad", "java_pft")).commits();
    for(RepoCommit commit: commits.iterate(new ImmutableMap.Builder<String, String>().build())){
      //System.out.println(commit);
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
