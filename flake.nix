{
  description = "Clojure Flake";

  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs?ref=nixos-unstable";
  };

  outputs =
    { self, nixpkgs }:
    let
      system = "x86_64-linux";
      pkgs = nixpkgs.legacyPackages.${system};
    in
    {
      devShells.${system}.default = pkgs.mkShell {
        packages = with pkgs; [
          # Uncomment for either runtime
          clojure
          # babashka

          # Tools for emacs
          # Formatter
          cljfmt
          clj-kondo

          # Leiningen
          leiningen

          # JDK Version
          jdk25

          # Nodejs
          nodejs_24

        ];
        env = {
          # Point Clojure to local project
          JAVA_OPTS = "-Xmx2g -XX:+UseG1GC";
          CLJ_CONFIG = "${builtins.getEnv "HOME"}/.clojure";
        };
        shellHook = ''
          echo "Clojure Ready!"
        '';
      };
    };
}
